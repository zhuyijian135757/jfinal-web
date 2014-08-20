$(function(){
	
	/*$.ajax({
		
		   type: "get",
		   url: "/blog/getBlogs",
		   success: function(msg){
		     alert( "Data Saved: " + msg );
		   }

	});*/
	
	$('#catch').bind("click",function(event){
	        event.stopPropagation();
	});
	
	$('#search').bind("click",function(event){
		
		var domain=$("input[name='domain']").val();
		$.ajax({
			   type: "GET",
			   url: "/blog/search?domain="+domain,
			   success: function(data){
				   if(data.id){
					   $("input[name='id']").val(data.attr);
					   $("input[name='value']").val(data.value);
				   }
			   }
		});
		
	});
	
	$('#catchHtml').bind("click",function(event){
        event.stopPropagation();
        
        var domain=$("input[name='domain']").val();
        var id=$("input[name='id']").val();
        var value=$("input[name='value']").val();
        var url=$("input[name='url']").val();
        var title=$("input[name='title']").val();
        var jtype=$("input[name='jtype']").val();
        
        $.ajax({
			   type: "POST",
			   url: "/blog/save",
			   data:"domain="+domain+"&id="+id+"&value="+value+"&url="+url+"&title="+title+"&jtype="+jtype,
			   success: function(data){
				   if(data.ret){
					   alert("catch success");
				   }else{
					   alert("catch fail");
				   }
			   }
		});
	});
	
})


function openMail(){
	
	$.ajax({
	   type: "get",
	   url: "/blog/openMail",
	   success: function(msg){
	   }
	});
	
}


function showSpan(e){
	
	if (e && e.stopPropagation)
        e.stopPropagation();
	else
        window.event.cancelBubble=true;
	
}


