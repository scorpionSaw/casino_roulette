<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<base href="/">
<title>Roulette</title>
<link rel="stylesheet" href="/assets/css/roulette.css?v=1.0">

<script type="text/javascript" src="/assets/js/prototype.js"></script>

<script type="text/javascript">

var numbers = "<%=request.getParameter("numbers") == null ? "" : request.getParameter("numbers")%>";

var numberList = new Array();

function onClickTd(number){
	
	//add
	numberList.push(number);
	
	if(numberList.length > 0){
		var url = "/roulette/getList/"+(numberList.toString())+"/?numbers=" + (numberList.toString());
		window.location = url;
	} else {
		var url = "/roulette/";
		window.location = url;
	}
	
}

function initPage(){

	if(numbers != ""){
		numberList = numbers.split(",");
	}
	
	var tds = document.getElementsByTagName("td");
	for(var i=0; i<tds.length; i++){
		var td = tds[i];
		
		if(numberList.indexOf(td.id.replace("number_","")) != -1){
			//td.style.backgroundColor = "greenYellow";
			td.addClassName("crossed");
		}
		
	}
	
	$("rouletteTable").observe("click", function(element){
		var clickedTd = event.findElement('td');
		if (clickedTd && clickedTd.id != "") {
			onClickTd(clickedTd.id.replace("number_", ""));
		}
	})
	
	$("resetBtn").observe("click", function(){
		window.location = "/roulette/";
	});
}


</script>

</head>
<body onload="initPage()">

<table id="rouletteTable" class="tapis">
	<tr>
		<td rowspan="3" id="number_0" class="cell zero">0</td>
		<td id="number_3" class="cell red">3</td>
		<td id="number_6" class="cell black">6</td>
		<td id="number_9" class="cell red">9</td>
		<td id="number_12" class="cell red">12</td>
		<td id="number_15" class="cell black">15</td>
		<td id="number_18" class="cell red">18</td>
		<td id="number_21" class="cell red">21</td>
		<td id="number_24" class="cell black">24</td>
		<td id="number_27" class="cell red">27</td>
		<td id="number_30" class="cell red">30</td>
		<td id="number_33" class="cell black">33</td>
		<td id="number_36" class="cell red">36</td>
	</tr>
	
	<tr>
		<td id="number_2" class="cell black">2</td>
		<td id="number_5" class="cell red">5</td>
		<td id="number_8" class="cell black">8</td>
		<td id="number_11" class="cell black">11</td>
		<td id="number_14" class="cell red">14</td>
		<td id="number_17" class="cell black">17</td>
		<td id="number_20" class="cell black">20</td>
		<td id="number_23" class="cell red">23</td>
		<td id="number_26" class="cell black">26</td>
		<td id="number_29" class="cell black">29</td>
		<td id="number_32" class="cell red">32</td>
		<td id="number_35" class="cell black">35</td>
	</tr>
	
	<tr>
		<td id="number_1" class="cell red">1</td>
		<td id="number_4" class="cell black">4</td>
		<td id="number_7" class="cell red">7</td>
		<td id="number_10" class="cell red">10</td>
		<td id="number_13" class="cell black">13</td>
		<td id="number_16" class="cell red">16</td>
		<td id="number_19" class="cell red">19</td>
		<td id="number_22" class="cell black">22</td>
		<td id="number_25" class="cell red">25</td>
		<td id="number_28" class="cell red">28</td>
		<td id="number_31" class="cell black">31</td>
		<td id="number_34" class="cell red">34</td>
	</tr>
</table>
<br/>
<div>
	<input type="button" value="Reset" id="resetBtn">
</div>
<br/>
<h3 style="text-decoration: underline;">Numéros à jouer : </h3>
<div class="o-layout">

		<table class="c-table c-table--striped">
			<tr>
			</tr>

			<tr style="width:250px;">
				${numbers}
			</tr>

		</table>

	</div>
</body>
</html>