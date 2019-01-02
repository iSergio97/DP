/*
 * CustomerController.java
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import domain.HandyWorker;
import domain.MessageBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import security.UserAccount;
import security.UserAccountRepository;
import services.HandyWorkerService;
import services.MessageBoxService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/handy-worker")
public class HandyWorkerController extends AbstractController {

    // Services ---------------------------------------------------------------
    @Autowired
    private HandyWorkerService handyWorkerService;
    @Autowired
    private MessageBoxService messageBoxService;
    @Autowired
    private UserAccountRepository userAccountRepository;


    // Constructors -----------------------------------------------------------

    public HandyWorkerController() {
        super();
    }

    // Register ---------------------------------------------------------------

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerGet() {
        ModelAndView result;
        HandyWorker handyWorker;
        handyWorker = this.handyWorkerService.create();
        result = new ModelAndView("handyWorker/register");
        result.addObject("handyWorker", handyWorker);
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerPost(HandyWorker handyWorker, final BindingResult binding) {
        ModelAndView result;

        if (!binding.hasErrors()) {
            UserAccount userAccount = handyWorker.getUserAccount();
            final Collection<MessageBox> messageBoxes = handyWorker.getMessageBoxes();
            handyWorker = this.handyWorkerService.save(handyWorker);

            final String password = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
            userAccount.setPassword(password);
            userAccount = this.userAccountRepository.save(userAccount);
            handyWorker.setUserAccount(userAccount);
            handyWorker = this.handyWorkerService.save(handyWorker);

            final ArrayList<MessageBox> savedMessageBoxes = new ArrayList<MessageBox>();
            for (MessageBox messageBox : this.messageBoxService.createSystemBoxes()) {
                messageBox.setActor(handyWorker);
                messageBox = this.messageBoxService.save(messageBox);
                savedMessageBoxes.add(messageBox);
            }
            handyWorker.setMessageBoxes(savedMessageBoxes);
            handyWorker = this.handyWorkerService.save(handyWorker);

            result = new ModelAndView("redirect:show.do");
        } else {
            result = new ModelAndView("customer/register");
            result.addObject("handyWorker", handyWorker);
            result.addObject("showError", binding);
            result.addObject("erroresBinding", binding.getAllErrors());
            for (int i = 0; i < binding.getAllErrors().size(); i++)
                System.out.println("Error " + i + binding.getAllErrors().get(i));
        }

        return result;
    }
}
