function createPage(count,index,size) {
	$(".jqueryPage").html("");
	$(".jqueryPage").pagination({
		count : count, // 总数
		size : size, // 每页数量
		index : index,// 当前页
		lrCount : 3,// 当前页左右最多显示的数量
		lCount : 1,// 最开始预留的数量
		rCount : 1,// 最后预留的数量
		callback : function(options) {
			searchAllStock(options.index);
		},
		beforeRender : function(jA) {
		}
	});
}

function latelyInfo(oneId,bigId){
	window.open("lately.html?oneId="+oneId+"&bigId="+bigId);
}

