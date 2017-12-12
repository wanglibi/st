var stockData,bigStockData;
function showStock(){
	var oneId = getParam("oneId");
	if(!oneId) return;
	
	$.ajax({
		url:'/stockweb/searchStockInfo',
   		type:'post',
   		//async: true,异步  默认值
   	    dataType:"json",
   	    data:{
   	    	oneId:oneId
   	    },
   	    success:function(json){
   	    	var xAxis = [];
   	    	var legendData = ['涨跌额'];
   	    	var series = {name:'涨跌额',type:'line',data:[]};
   	    	
   	    	$.each(json.datas,function(index,item){
   	    		xAxis[index] = item.stDate;
   	    		series.data[index] = parseInt(item.stClosePrice);
   	    	});
   	    	stockData = json.datas;
   	    	showLine1($("#stock").get(0),xAxis,series,json.datas[0].stName,legendData);
   	    },
   	 	error:function(data){
   	 		console.log(data);
       	}
	});
}

function showBigStock(){
	var oneId = getParam("oneId");
	var bigId = getParam("bigId");
	if(!bigId || !oneId) return;
	
	$.ajax({
		url:'/stockweb/searchBigStockInfo',
   		type:'post',
   		//async: true,异步  默认值
   	    dataType:"json",
   	    data:{
   	    	bigId:bigId,
   	    	oneId:oneId
   	    },
   	    success:function(json){
   	    	var xAxis = [];
   	    	var legendData = ['涨跌额'];
   	    	var series = {name:'涨跌额',type:'line',data:[]};
   	    	
   	    	$.each(json.datas,function(index,item){
   	    		xAxis[index] = item.stDate;
   	    		series.data[index] = parseInt(item.stClosePrice);
   	    	});
   	    	bigStockData = json.datas;
   	    	showLine1($("#bigStock").get(0),xAxis,series,json.datas[0].stName,legendData);
   	    },
   	 	error:function(data){
   	 		console.log(data);
       	}
	});
}

//显示区域，横坐标，数据集，图名称
function showLine(div,xAxis,series,text){
	if(!xAxis || !series)
		return;
	
	var myChart = echarts.init(div);
	// 指定图表的配置项和数据
	var option = {
	    title: {
	        text: text
	    },
	    tooltip: {
	    	type : 'shadow',
	    	formatter: "{c}"
	    },
	    legend: {
	        data:['涨跌额']
	    },
	    xAxis: {
	    	
	        data: xAxis,
	        //字体倾斜
	        //axisLabel:{
	        //	rotate:45
	        //}
	    },
	    yAxis: {},
	    series: [{
	        name: '涨跌额',
	        type: 'line',
	        data: series
	    }],
	    dataZoom: [{
            show: true,
            height: 50,
            type: 'slider',//slider表示有滑动块的，inside表示内置的
            xAxisIndex: [0],
            start: 70,
            end: 100
        }]
	};
	myChart.setOption(option);
}

function showStockYear(){
	if(!stockData){
		console.log('stockData is null!');
		return;
	} 
	var xAxis = [];
	var legendData = [];
	var series = [];
	var seriesData = [];
	var i = -1,x = 0;y = 0;
	$.each(stockData,function(index,item){
		var year = item.stDate.substr(0,4);
		if(legendData.length == 0 || legendData[i] != year){
			i++;y = 0;
			legendData[i] = year;
			series[i] = new Object();
			series[i].name= year;
			series[i].type='line';
			series[i].data = new Array();
		}
		
		if(legendData[0] == year){
			xAxis[x] = item.stDate;
			x++;
		}
		if(legendData[i] = year){
			//seriesData[y] = parseInt(item.stClosePrice);
			series[i].data[y] = parseInt(item.stClosePrice);
			y++;
		}
	});
	showLine1($("#stock").get(0),xAxis,series,stockData[0].stName,legendData);
}


function showLine1(div,xAxis,series,text,legendData){
	if(!xAxis || !series)
		return;
	
	var myChart = echarts.init(div);
	// 指定图表的配置项和数据
	var option = {
	    title: {
	        text: text
	    },
	    tooltip: {
	    	type : 'shadow',
	    	formatter: "{c}"
	    },
	    legend: {
	        data:legendData
	    },
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
	myChart.setOption(option);
}