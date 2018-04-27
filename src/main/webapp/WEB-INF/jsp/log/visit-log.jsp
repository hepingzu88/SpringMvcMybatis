<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/include/common.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@include file="/include/common_style.jsp" %>
  <%@include file="/include/common_js.jsp" %>
  
  <title>Insert title here</title>
  <script src="/highcharts/highcharts.js"></script>
	<script src="/highcharts/exporting.js"></script>
	
	 <!-- <script src="http://code.highcharts.com/highcharts.js"></script> -->
</head>
<body>


<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">  
/* var data = new Array[20]; */
$(document).ready(function() {  
    Highcharts.setOptions({  
        global: {  
            useUTC: false  
        }  
    });  
    var chart;  
    chart = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container',  
            type: 'spline',  
            marginRight: 10,  
            events: {  
                load: function() {  
                    // set up the updating of the chart each second  
                    var series = this.series[0];  
                    setInterval(function() {  
                        var x = (new Date()).getTime(); // current time  
                        $.post("/log/visit-times",function(res){
                        		 series.addPoint([x, res], true, true); 
                        	})
                    }, 5000);  
                }  
            }  
        },  
        title: {  
            text: '<b>客户访问次数统计</b>'  
        },  
        xAxis: {  
            type: 'datetime',  
            tickPixelInterval: 150  
        },  
        yAxis: {  
            title: {  
                text: '单位：次'  
            },
          
            plotLines: [{  
                value: 0,  
                width: 2,  
                color: '#808080'  
            }]  
        },  
        tooltip: {  
            formatter: function() {  
                    return '<b>'+ this.series.name +'</b><br/>'+  
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+  
                    Highcharts.numberFormat(this.y, 0);  
            }  
        },  
        legend: {  
            enabled: false  
        },  
        exporting: {  
            enabled: false  
        },  
        series: [{  
            name: 'real data',  
            data: (function() {  
                // generate an array of random data  
                var data = [],  
                    time = (new Date()).getTime(),  
                    i;  
  
                for (i = -19; i <= 0; i++) {  
                    data.push({  
                        x: time + i * 5000,  
                        y: Math.ceil(Math.random()*10) 
                    });  
                }  
                return data;  
            })()  
        }]  
    });  
  
});  
</script> 
</body>
<%-- <body>
 <div id="main" style="height:400px"></div>
    <script src="${basePath}/echarts-3.8/echarts.min.js"></script>
    
    <script type="text/javascript">
 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
 	function getTime(){
 		var now = new Date();
 	/* 	getUTCHours()	根据世界时返回 Date 对象的小时 (0 ~ 23)。
 		getUTCMinutes()	根据世界时返回 Date 对象的分钟 (0 ~ 59)。
 		getUTCSeconds()	根据世界时返回 Date 对象的秒钟 (0 ~ 59)。 */
 		return now.toLocaleTimeString();
 	}
    
 
    var data = new Array(50);
    var times = new Array(50);
    var now = new Date();
    for(var i=49; i>=0; i--){
    	times[i] = new Date(now - 5*(49-i)*1000).toLocaleTimeString();
    }
 	setInterval(function () {
 		
 		$.post("/log/visit-times",function(res){
			if(data.length>=50){
				data.shift();
				times.shift();
			}
			data.push(res);
			times.push(getTime());
			console.log(data);
			 myChart.setOption({
				 	xAxis : [{data:times}],
		            series: [{
		                data: data
		            }]
		        });
			
		 })}, 5000);
 		
 		
 		option = {
 		    tooltip : {
 		        trigger: 'axis'
 		    },
 		    legend: {
 		        data:['访问数量']
 		    },
 		    toolbox: {
 		        show : true,
 		        feature : {
 		            mark : {show: true},
 		            dataView : {show: true, readOnly: false},
 		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
 		            restore : {show: true},
 		            saveAsImage : {show: true}
 		        }
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		            type : 'category',
 		            boundaryGap : false,
 		            data : times
 		        }
 		    ],
 		    yAxis : [
 		        {
 		            type : 'value'
 		        }
 		    ],
 		    series : [
 		        {
 		            name:'访问数量',
 		            type:'line',
 		            stack: '总量',
 		            data:data
 		        }
 		        
 		    ]
 		};
 
 	 myChart.setOption(option);
    /* function randomData() {
        now = new Date(+now + oneDay);
        value = value + Math.random() * 21 - 10;
        return {
            name: now.toString(),
            value: [
                [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
                Math.round(value)
            ]
        }
    }

    var data = [];
    var now = +new Date(1997, 9, 3);
    var oneDay = 24 * 3600 * 1000;
    var value = Math.random() * 1000;
    for (var i = 0; i < 1000; i++) {
        data.push(randomData());
    }

    option = {
        title: {
            text: '动态数据 + 时间坐标轴'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: data
        }]
    };

    setInterval(function () {

        for (var i = 0; i < 5; i++) {
            data.shift();
            data.push(randomData());
        }

        myChart.setOption({
            series: [{
                data: data
            }]
        });
    }, 1000);
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
     */
    </script>
</body> --%>


</html>