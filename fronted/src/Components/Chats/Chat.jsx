import { useEffect, useState } from "react";
import'./Chat.css'

function Chat(props){

    const[messages, setMessages] = useState([])

    async function getAllMessages() {
      try {
      const response = await fetch(`http://localhost:8080/api/users/`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem("token")}`,
          'Content-Type': 'application/json'
        },
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data)
      } 
      else if (response.status == 401) {
        // await refersh()
      }
      else {
        alert('Ошибка при отправке данных');
        console.log(response.status)
      } 
      } catch (error) {
        console.error('Ошибка:', error);

      }
    }

    return(
        <div> 
            <div className="userInfo_chat">
              <div className="userName_chat">{props.username}</div>
            </div>
            <div className="push_chat">
              <input type="text" className="input_chat"/>
              <button className="chat_button">send</button>
            </div>
        </div>
    )
}
export default Chat;