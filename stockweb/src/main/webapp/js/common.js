function getParam(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = decodeURI(window.location.search.substr(1)).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function converMoney(money){
	if(money<-100000000){
		return parseInt(money/100000000)+" 亿元";
	}else if(money<-10000){
		return parseInt(money/10000)+" 万元";
	}else if(money<10000){
		return parseInt(money)+" 元";
	}else if(money<100000000){
		return parseInt(money/10000)+" 万元";
	}else{
		return parseInt(money/100000000)+" 亿元";
	}
}

