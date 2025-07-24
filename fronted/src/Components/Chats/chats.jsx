import { useState } from 'react';
import './Chat.css'
import ChatInfo from './Chat_info';
import Chat from './Chat';
import ReactDOM from 'react-dom/client';

function Chats(){
    const [messangeinfo, setMessangeInfo] = useState([{name:"Петя", time:"10:10", text:"kek", img :'./IMG/photo_2025-05-13_21-57-23.jpg', chatId:1},
         {name:"Петя", time:"10:10", text:"keke", img:'./IMG/photo_2024-12-04_13-05-55.jpg', chatId:2}])

    const [chooseChat, setchooseChat] = useState(0);
    function clickHandler(id){
        setchooseChat(id)
    }
    return(
        <div className='chats_block'>
            <div className="folder_block">
                <div className="user_block">
                    <div className="user_folder_photo"></div>
                    <input className="user_folder_search" placeholder='Поиск'/>
                </div>
                <div className="folder">
                    {messangeinfo.map(item => 
                        <ChatInfo
                            name={item.name}
                            text = {item.text}
                            time={item.time}
                            img={item.img}
                            func={() => clickHandler(item.chatId)}
                        />
                    )}
                </div>
            </div>
            <div className='chat_block'>
                <Chat
                    id={chooseChat}
                />
            </div>
        </div>
    )
    
}

export default Chats;