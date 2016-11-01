<?php
    $email_to = "claus00100@gmail.com";
    $email_subject = "Email desde la web";
	
    function errorMsg($error) {
		echo $error
       //header ("location: ..\page_error_gen.html?msg=$error");
        die();
    }
	function successMsg() {
		echo "exito"
        //header ("location: ..\page_contact_success.html");
		die();
    }
    // validation expected data exists
    if(!isset($_POST['contact_name']) || !isset($_POST['contact_email']) || !isset($_POST['contact_comment'])) {
        errorMsg('Lo sentimos, al parecer no completó el formulario correctamente.');       
    }

    $contact_name = $_POST['contact_name']; // required
    $email_from = $_POST['contact_email']; // required
    $contact_comment = $_POST['contact_comment']; // required
    
	$email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';
	if(!preg_match($email_exp,$email_from)) {
		errorMsg('La dirección de correo ingresada parece inválida.');
	}
	
    $string_exp = "/^[A-Za-z .'-]+$/";
	if(!preg_match($string_exp,$contact_name)) {
		errorMsg('El nombre ingresado parece inválido.');
	}
 
	if(strlen($contact_comment) < 2) {
		errorMsg('El comentario ingresado parece inválido.');
	}
 
    $email_message = "Form details below.\n\n";
 
     
 
    function clean_string($string) {
      $bad = array("content-type","bcc:","to:","cc:","href");
      return str_replace($bad,"",$string);
    }
    $email_message .= "Nombre: ".clean_string($contact_name)."\n";
    $email_message .= "Email: ".clean_string($email_from)."\n";
    $email_message .= "Comentario: ".clean_string($contact_comment)."\n";

// create email headers
 
$headers = 'From: '.$email_from."\r\n".
'Reply-To: '.$email_from."\r\n".
'X-Mailer: PHP/' . phpversion();
	mail($email_to, $email_subject, $email_message, $headers)
  /*if(mail($email_to, $email_subject, $email_message, $headers)){  
		success();
	}else{
		errorMsg("Error desconocido al enviar el correo.");
	}*/
 
?>