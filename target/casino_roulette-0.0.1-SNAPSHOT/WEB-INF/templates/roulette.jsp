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
			td.style.backgroundColor = "greenYellow";
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

<table id="rouletteTable">
	<tr>
		<td rowspan="3" id="number_0">0</td>
		<td id="number_3">3</td>
		<td id="number_6">6</td>
		<td id="number_9">9</td>
		<td id="number_12">12</td>
		<td id="number_15">15</td>
		<td id="number_18">18</td>
		<td id="number_21">21</td>
		<td id="number_24">24</td>
		<td id="number_27">27</td>
		<td id="number_30">30</td>
		<td id="number_33">33</td>
		<td id="number_36">36</td>
	</tr>
	
	<tr>
		<td id="number_2">2</td>
		<td id="number_5">5</td>
		<td id="number_8">8</td>
		<td id="number_11">11</td>
		<td id="number_14">14</td>
		<td id="number_17">17</td>
		<td id="number_20">20</td>
		<td id="number_23">23</td>
		<td id="number_26">26</td>
		<td id="number_29">29</td>
		<td id="number_32">32</td>
		<td id="number_35">35</td>
	</tr>
	
	<tr>
		<td id="number_1">1</td>
		<td id="number_4">4</td>
		<td id="number_7">7</td>
		<td id="number_10">10</td>
		<td id="number_13">13</td>
		<td id="number_16">16</td>
		<td id="number_19">19</td>
		<td id="number_22">22</td>
		<td id="number_25">25</td>
		<td id="number_28">28</td>
		<td id="number_31">31</td>
		<td id="number_34">34</td>
		
		<td rowspan="3">
			<input type="button" value="Reset" id="resetBtn">
		</td>	
	</tr>
</table>
<br/>
<div class="o-layout">

		<table class="c-table c-table--striped">
			<tr>
				<th>Numéros à jouer</th>
			</tr>

			<tr>
				<td style="width:250px;">${numbers}</td>
			</tr>

		</table>

	</div>
</body>
</html>