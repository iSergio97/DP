<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Action 3 controller Customer </title>
</head>
<body>

	<form action="" method="POST">
	<input type="hidden" name="ticker" id = "ticker" value="">
	<input type="hidden" name="moment" id = "moment" value="">
	
	<div>
	<label for="description"> Description: </label>
	<@spring.bind "command.description">
	<input type="text"
	name="description"
	value="" /> <br>
	<#list spring.status.errorMessages as error> <b>${error} </b> <br>
	</#list>
	</@spring.bind>
	</div>
	
	<div>
	<label for="address"> Address: </label>
	<@spring.bind "command.description">
	<input type="text"
	name="address"
	value="" /> <br>
	<#list spring.status.errorMessages as error> <b>${error} </b> <br>
	</#list>
	</@spring.bind>
	</div>
	
	<div>
	<label for="maximumPrice"> Maximum Price: </label>
	<@spring.bind "command.description">
	<input type="number"
	name="maximunPrice"
	value="" /> <br>
	<#list spring.status.errorMessages as error> <b>${error} </b> <br>
	</#list>
	</@spring.bind>
	</div>
	
	<div>
	<label for="timeLimit"> Time limit: </label>
	<@spring.bind "command.description">
	<input type="date"
	name="timeLimit"
	value="" /> <br>
	<#list spring.status.errorMessages as error> <b>${error} </b> <br>
	</#list>
	</@spring.bind>
	</div>
	
	<div>
	<label for="fixUpTaskCategory"> Fix-Up Task Category: </label>
	<@spring.bind "command.description">
	<select>
	<#list [fixUpTaskCategory] as x>
	${x.name}
	 </#list> 
	  </select>
	<br>
	<#list spring.status.errorMessages as error> <b>${error} </b> <br>
	</#list>
	</@spring.bind>
	</div>
	
	<div>
	  <input type="submit" value="submit" />
	 </div>
	
	 <div>
	  <a href="www.acme-handy-worker.com/home.do" />
	 </div>
	  
	  </form>
</body>

</html>