
var seconds = 07;  
var campo = document.getElementById('cronometro');  
var campo_div = document.getElementById('cronometro_div');  
  
function startCountdown() {       
        seconds-=1;  
        document.getElementById('cronometro').innerHTML = " " + seconds;
        setTimeout("startCountdown()",1000);
}  

function startCountdownReset() { 
    seconds-=1;  
    document.getElementById('cronometro').innerHTML = " " + seconds;
    setTimeout("startCountdownReset()",1000);	
}  

function radioButton(radio, question) {
	 var id = radio.name.substring(radio.name.lastIndexOf(':'));	 	 
	 var el =  document.getElementById(question).querySelectorAll('*');
	 
	 for (var i = 0; i < el.length; i++) {
		if (el[i].name != null){
			if (el[i].name.substring(el[i].name.lastIndexOf(':')) == id) {
		 		el[i].checked = false;
		 	}
		}

	 }
	 
	 radio.checked = true;
	 
}
//
//
//function valida_horas(edit) {
//	var el =  document.getElementById(edit).querySelectorAll('*');	
//	for (var i = 0; i < el.length; i++) {		
//		if(el[i].value.length == 2){	
// 			el[i].value += ":"; 			
// 	    }
//	 }
//	
//}
//
//function verifica_horas(obj) {
//	
//	var el =  document.getElementById(obj).querySelectorAll('*');	
//	
//	for (var i = 0; i < el.length; i++) {		
//		if(el[i].value.length < 5)
//			el[i].value = '';
//	    else {
//	        hr = parseInt(el[i].value.substring(0,2));
//	        mi = parseInt(el[i].value.substring(3,5));
//
//	        if((hr < 0 || hr > 23) || (mi < 0 || mi > 60)) {
//	        	el[i].value = '';
//	            return alert('Hora inválida');
//	        }
//	    }
//	}
//	
//    
//}