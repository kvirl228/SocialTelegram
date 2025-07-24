import { useState } from 'react'
import './Forms.css'
function Login(){
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')

    const handleChangeName = (event) => {
        setName(event.target.value)
    }

    const handleChangePassword = (event) => {
        setPassword(event.target.value)
    }

    const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/auth/signin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
           username: name, 
            password: password
        }),
        mode: 'cors'
      });

      if(response.ok){
            const token = await response.text()
            localStorage.setItem("token", token)
            console.log(token)
            alert("NICE")
        }
        else{
            alert( name)
            alert(password)
        }
    } catch (error) {
      console.error('Ошибка:', error);
    }
  };
    return(
        <div className="forms_block">
            <form className="forms">
                <div className="main_form_text">Вход</div>
                <div className="form_block">
                    <label className="form_label">Имя</label>
                    <input className="form_input" value = {name} onChange={handleChangeName}/>
                </div>
                <div className="form_block">
                    <label className="form_label" >Пароль</label>
                    <input className="form_input" value={password} onChange={handleChangePassword}/>
                </div> 
                <button className="form_btn" onClick={handleSubmit}>Войти</button>
                <div className="little_form_text">Зарегестрироваться</div>
            </form>
        </div>
    )
}
export default Login;