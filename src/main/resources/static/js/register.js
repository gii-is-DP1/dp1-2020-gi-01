$(document).ready(function() {
    $('#reg-key').focusout(
        function(event) {
            var key = $('#reg-key').val();         
            var data = '?key='
                    + encodeURIComponent(key);
            $.ajax({
                url : '/register/check-key'+data,
                type : "GET",
 
                success : function(response) {
                	if(response=='true'){
                		$('#reg-key-msg').text('Welcome to the crew!');
                	}else{
                		$('#reg-key-msg').text('Invalid key!');
                	}
					
                },
                error : function(xhr, status, error) {
                    $('#reg-key-msg').text('Error checking key');
                }
            });
            return false;
        });
    });

function showMe (box) {

    var chboxs = document.getElementById("show-emp");
	var input = document.getElementById("reg-key");
  /*  var vis = "none";
    for(var i=0;i<chboxs.length;i++) { 
        if(chboxs[i].checked){
         vis = "block";
            break;
        }
    }
	$('#input').addClass('invisible');
    Creado un script mucho más sencillo y menos problemático:
		*/
	if(chboxs.checked){
		
		input.disabled = false;
		/*if(input.classList.contains("invisible"))
		{
			input.classList.remove('invisible');
			input.classList.add('visible');
		}else 
		{
			input.classList.add('visible');
		}*/
		
	}else
	{
		if(input.disabled == false)
		{			
			input.disabled = true;
		}
		
		/*if(input.classList.contains("visible"))
		{
			input.classList.remove('visible');
			input.classList.add('invisible');
		}else 
		{
			input.classList.add('invisible');
		}*/
	}
   /* document.getElementById(box).style.display = vis; */
}



