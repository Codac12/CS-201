// $(document).ready(function(){

document.getElementById("myBtn").addEventListener("mouseover", random);
//this is used for random1 which swiches the locatoin
var button_location = {top: 200, left: 650 };
var button_begining_size = {height:40, width:180};
var button_current_size = {height:40, width:180};
var button_phase = 1;
var attempts = 0;


function random() {

    var random = Math.floor(Math.random() * (3 + 1 ));
    attempts = attempts + 1;
    document.getElementById("attempts").innerHTML = attempts;
    

    switch(random){
    	case 1:
    		random1();
    		break;
    	case 2:
    		random2();
    		break;
    	case 3:
    		random3();
    		break;
    	case 0:
    		random4();
    		break;
    }
}

function random2()
{
	document.getElementById("myBtn").style.display = "none";
	setTimeout(function()
	{
		document.getElementById("myBtn").style.display = "initial";
	},5000);
};

function random1()
{
	var choice =  parseInt(Math.random() + .5);
	var choice2 = parseInt(Math.random() + .5);
	if(choice == 1)
	{
		if(choice2 == 1)
		{
			button_location.left = button_location.left - 50;
			document.getElementById("myBtn").style.left = button_location.left + "px";
		}
		else
		{
			button_location.left = button_location.left + 50;
			document.getElementById("myBtn").style.left = button_location.left + "px";
		}

	}
	else
	{
		if(choice2 == 1)
		{
			button_location.top = button_location.top + 50;
			document.getElementById("myBtn").style.top = button_location.top +"px" ;
		}
		else
		{
			button_location.top = button_location.top - 50;
			document.getElementById("myBtn").style.top = button_location.top +"px" ;
		}

	}
	

};

function random3()
{
var mybutton = document.getElementById("myBtn");
var title_words = document.getElementById("title_words");

if(button_phase == 1)
{
button_current_size.height = button_current_size.height + 20 ;
mybutton.style.height = button_current_size.height + "px";

button_current_size.width = button_current_size.width + 40 ;
mybutton.style.width = button_current_size.width + "px";

button_location.top = button_location.top + 100;
			document.getElementById("myBtn").style.top = button_location.top +"px" ;

title_words.innerHTML = "There just trying to help you";

button_phase = 2;
}
else if(button_phase == 2) 
{

button_current_size.height = button_current_size.height + 20 ;
mybutton.style.height = button_current_size.height + "px";

button_current_size.width = button_current_size.width + 40 ;
mybutton.style.width = button_current_size.width + "px";

button_location.top = button_location.top + 100;
document.getElementById("myBtn").style.top = button_location.top +"px" ;

button_phase = 3;

title_words.innerHTML = "Kool ill make it even easier";
}else
{
mybutton.style.height = button_begining_size.height + "px";
mybutton.style.width = button_begining_size.width + "px";

button_location.top = button_location.top - 200;
document.getElementById("myBtn").style.top = button_location.top +"px" ;

button_phase = 1;

title_words.innerHTML = "Screw it";
}



}

function random4()
{
	var win = window.open('https://www.youtube.com/watch?v=TjcOJmoJwpk', '_blank');
if (win) {
    
    document.getElementById("title_words").innerHTML = "thought you might need some inspirational music";
} else {
    
    alert('NO FUN :( ... allow popups for this website');
}
}

// });
