var stockData,bigStockData;

function showSt(){
	if(!getParam("oneId")) return;
	findStock("/stockweb/searchStockInfo",{oneId:getParam("oneId")},$("#stock").get(0));
}

function showBigSt(){
	if(!getParam("bigId") || !getParam("oneId")) return;
	findStock("/stockweb/searchBigStockInfo",{oneId:getParam("oneId"),bigId:getParam("bigId")},$("#bigStock").get(0));
}

function showStYear(){
	showYear(stockData,$("#stock").get(0));
}	
function showBigStYear(){
	showYear(bigStockData,$("#bigStock").get(0));
}

function findStock(url,data,div){
	$.ajax({
		url:url,
   		type:'post',
   		//async: true,异步  默认值
   	    dataType:"json",
   	    data:data,
   	    success:function(json){
   	    	var xAxis = [];
   	    	var legendData = ['涨跌额'];
   	    	var series = {name:'涨跌额',type:'line',data:[]};
   	    	
   	    	$.each(json.datas,function(index,item){
   	    		xAxis[index] = item.stDate;
   	    		series.data[index] = item.stClosePrice;
   	    	});
   	    	
   	    	if(div.id == "stock"){
   	    		stockData = json.datas;
   	    	}else{
   	    		bigStockData = json.datas;
   	    	}
   	    	var legend = {data:legendData};
   	    	showLine(div,xAxis,series,json.datas[0].stName,legend);
   	    },
   	 	error:function(data){
   	 		console.log(data);
       	}
	});
}

function showYear(stData,div){
	if(!stData){
		console.log('stockData is null!');
		return;
	} 
	var xAxis = [];
	var legendData = [];
	var legendSelect = {};
	var series = [];
	var seriesData = [];
	var i = -1,x = 0;y = 0;
	$.each(stData,function(index,item){
		var year = item.stDate.substr(0,4);
		if(legendData.length == 0 || legendData[i] != year){
			i++;y = 0;
			legendData[i] = year;
			series[i] = new Object();
			series[i].name= year;
			series[i].type='line';
			series[i].data = new Array();
			
			if(year == new Date().getFullYear()){
				legendSelect[year]=true;
			}else{
				legendSelect[year]=false;
			}
		}
		
		if(legendData[0] == year){
			xAxis[x] = item.stDate.substr(5,9);
			x++;
		}
		
		
		if(legendData[i] = year){
			series[i].data[y] = item.stClosePrice;
			y++;
		}
	});
	var legend = {data:legendData,selected:legendSelect};
	showLine(div,xAxis,series,stData[0].stName,legend);
}


function showLine(div,xAxis,series,text,legend){
	if(!xAxis || !series)
		return;
	
	var myChart = echarts.init(div);
	// 指定图表的配置项和数据
	var option = {
	    title: {
	    	top:25,
	    	left:20,
	        text: text
	    },
	    tooltip: {
	    	type : 'shadow',
	    	formatter: "{c}"
	    },
	    legend: legend,
	    xAxis: {
	    	
	        data: xAxis,
	        //字体倾斜
	        //axisLabel:{
	        //	rotate:45
	        //}
	    },
	    yAxis: {},
	    series: series,
	    dataZoom: [{
            show: true,
            height: 50,
            type: 'slider',//slider表示有滑动块的，inside表示内置的
            xAxisIndex: [0],
            start: 0,
            end: 100
        }]
	};
	myChart.setOption(option,true);
}