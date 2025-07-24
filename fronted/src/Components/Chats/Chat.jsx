import { useState } from "react";
import'./Chat.css'

function Chat(props){
    const [messanges, setMessanges] = useState([{mes: "wasap"}, {mes:"wasap"}])
    const [messanges2, setMessanges2] = useState([{mes: "wasap2"}, {mes:"wasap2"}])
    let chat = <div></div>;
    function currentChat(id){
        if(id == 1){
            chat = <div className="mess">{messanges.map((item) => item.mes)}</div>
        }
        else if(id == 2){
            chat = <div className="mess">{messanges2.map((item) => item.mes)}</div>
        }
    }
    return(
        <div>
            {currentChat(props.id)}
            {chat}
        </div>
    )
}
export default Chat;