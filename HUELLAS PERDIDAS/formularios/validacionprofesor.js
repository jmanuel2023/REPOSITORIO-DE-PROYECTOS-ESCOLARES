const formulario=document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
    matricula: /^[0-9]{10}$/,
	nombrep: /^[a-zA-ZÀ-ÿ\s]{1,20}$/, //Letras y espacios, pueden llevar acentos.
	primerp: /^[a-zA-ZÀ-ÿ\s]{1,15}$/, //Letras y espacios, pueden llevar acentos.
    segundop: /^[a-zA-ZÀ-ÿ\s]{1,15}$/, //Letras y espacios, pueden llevar acentos.
	correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	pass: /^.{4,12}$/ // 4 a 12 digitos.
}

const campos = {
	matricula: false,
    nombrep: false,
    primerp: false,
    segundop: false,
    correo: false,
    pass: false
}

const validarFormulario = (e) => {
	switch (e.target.name) {
		case "matricula":
			validarCampo(expresiones.matricula, e.target, 'matricula');
		break;
		case "nombrep":
			validarCampo(expresiones.nombrep, e.target, 'nombrep');
		break;
		case "primerp":
			validarCampo(expresiones.primerp, e.target, 'primerp');
		break;
		case "segundop":
			validarCampo(expresiones.segundop, e.target, 'segundop');
		break;
		case "correo":
			validarCampo(expresiones.correo, e.target, 'correo');
		break;
		case "pass":
			validarCampo(expresiones.pass, e.target, 'pass');
		break;
		case "pass2":
			validarPassconf();
		break;
		
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}
const validarPassconf = () => {
	const inputPassword1 = document.getElementById('pass');
	const inputPassword2 = document.getElementById('pass2');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`grupo__pass2`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__pass2`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__pass2 .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos['pass'] = false;
	} else {
		document.getElementById(`grupo__pass2`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__pass2`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__pass2 .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos['pass'] = true;
	}
}



inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});