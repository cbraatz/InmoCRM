<?php
    $email_to = "claus00100@gmail.com";
 
    $email_subject = "Email desde la web";
 
     
 
     
 
    function errorMsg($error) {
		header ("location: ..\page_error_gen.html?msg=$error");
		die();
    }
	function successMsg() {
		header ("location: ..\page_contact_success.html");
	}
    // validation expected data exists
 
    if(!isset($_POST['contact_name']) || !isset($_POST['contact_email']) || !isset($_POST['contact_comment'])) {
		echo "F 2.";
        errorMsg('Lo sentimos, al parecer no completó el formulario correctamente.');       
    }
 
     echo "Opc 2.";
 
    $contact_name = $_POST['contact_name']; // required
 
    $email_from = $_POST['contact_email']; // required
 
    $contact_comment = $_POST['contact_comment']; // required
 
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';
 
	if(strlen($contact_name) <= 0 || strlen($email_from) <= 0 || strlen($contact_comment) <= 0 ) {
        errorMsg('Lo sentimos, al parecer alguno de los campos no tiene valores cargados.');       
    }
	
  if(!preg_match($email_exp,$email_from)) {
    errorMsg('El email ingresada parece incorrecta.');
 
  }
 
    $string_exp = "/^[A-Za-z .'-]+$/";
 
  if(!preg_match($string_exp,$contact_name)) {
    errorMsg('El nombre ingresado parece incorrecto.');
 
  }
 
  if(strlen($contact_comment) < 2) {
    errorMsg('El comentario ingresado parece muy corto.');
 
  }
 
    $email_message = "Los detalles del formulario aparecen a continuación:\n\n";
 
     
 
    function clean_string($string) {
		echo "F 7.";
      $bad = array("content-type","bcc:","to:","cc:","href");
 
      return str_replace($bad,"",$string);
 
    }
 
     echo "Opc 4.";
 
    $email_message .= "Nombre: ".clean_string($contact_name)."\n";
 
    $email_message .= "Email: ".clean_string($email_from)."\n";
 
    $email_message .= "Comentario: ".clean_string($contact_comment)."\n";
 
     
 
     
 
// create email headers
 
//$headers = 'From: '.$email_from."\r\n".
$headers = 'From: '."formulario_consulta_web@noreplay.com\r\n". 
'Reply-To: '.$email_from."\r\n" .
 
'X-Mailer: PHP/' . phpversion();
 echo "Opc 5. Antes de enviar el email:";
 if(mail($email_to, $email_subject, $email_message, $headers)){  
	successMsg();
 }else{
	errorMsg("Error desconocido al enviar el email.");
 }
 
?>