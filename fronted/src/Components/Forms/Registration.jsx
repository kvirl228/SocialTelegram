import { useState } from 'react';
import './Forms.css'
function Registration(){

    
    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleChangeName = (event) => {
        setName(event.target.value)
        
    }

    const handleChangeEmail = (event) => {
        setEmail(event.target.value)
        
    }

    const handleChangePassword = (event) => {
        setPassword(event.target.value)
        
    }

    

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/auth/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username:name,
            email:email,
            password:password
        }),
        mode: 'cors'
      });

      if (response.ok) {
        alert('Данные успешно отправлены!');
      } else {
        alert('Ошибка при отправке данных');
      }
    } catch (error) {
      console.error('Ошибка:', error);
    }
  };

    return(
        <div className="forms_block">
            <form className="forms_register">
                <div className="main_form_text">Регистрация</div>
                <div className="form_block">
                    <label className="form_label">Имя</label>
                    <input className="form_input" value={name} onChange={handleChangeName}/>
                </div>
                <div className="form_block">
                    <label className="form_label">Почта</label>
                    <input className="form_input" value={email} onChange={handleChangeEmail}/>
                </div>
                <div className="form_block">
                    <label className="form_label">Пароль</label>
                    <input className="form_input" value={password} onChange={handleChangePassword}/>
                </div> 
                <button className="form_btn" onClick={handleSubmit}>Создать</button>
                <button className="little_form_text">Войти</button>
            </form>
        </div>
    )
}

export default Registration;