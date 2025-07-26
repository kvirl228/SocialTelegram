import { useState } from "react";
import'./Chat.css'

function Chat(props){
    const[chat, setChat] = useState(<div>111</div>)
    const[messages, setMessages] = useState([])
    function currentChat(id){
       if (id != -1){
        setChat(<div>AAAAAAAAAAAAAAA</div>)
       }
    }

    function getMessage(){

    }

    return(
        <div>
            {currentChat(props.id)}
            {chat}

            <input type="text"/>
        </div>
    )
}
export default Chat;