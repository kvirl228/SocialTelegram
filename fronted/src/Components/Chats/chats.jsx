import { useEffect, useState } from 'react';
import './Chat.css'
import ChatInfo from './Chat_info';
import { useNavigate } from 'react-router-dom';
import Chat from './Chat';
import Settings from '../Forms/Settings';

function Chats(){

    const navigate = useNavigate()

    const[searchCheck, setSearchCheck] = useState(true)
    const[isClick, setIsClick] = useState(true)
    const[isOpenSettings, setIsOpenSettings] = useState(true)

    const[userid, setuserId] = useState(-1)
    const[chooseCaht, setChooseChat] = useState(-1)

    const[search, setSearch] = useState('')
    
    const[userInfo, setUserInfo] = useState([{username:'', id:-1}])
    const[userChats, setUserChats] = useState([])


    const handleChange = (e) => {
      setSearch(e.target.value)
      if(e.target.value.trim() == ''){
          setSearchCheck(true)
      }
    }

    const clickChat = (value, chatId) => {
      setIsClick(!value)
      setChooseChat(chatId)
    }

    const openSettings = (value) => {
      setIsOpenSettings(!value)
    }

    async function refersh() {
      try {
        const response = await fetch("http://localhost:8080/auth/refresh", {
          method: "POST",
          credentials: "include", 
          headers: {
            "Content-Type": "application/json"
          }
        });

        if (response.ok) {
          const token = await response.text()
          localStorage.setItem("token", token)
        } else {
          navigate('/')
        }
      } catch (error) {
        navigate('/')
      }
    }
    
    async function searchUser(){
      try {
      const response = await fetch(`http://localhost:8080/api/users/${search}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem("token")}`,
          'Content-Type': 'application/json'
        },
      });

      if (response.ok) {
        alert("Круто")
        const data = await response.json();
        console.log(data)
        await setUserInfo([ data])
        console.log(userInfo[0].id)
        // await setChooseChat(userInfo[0].Id)
        setSearchCheck(false)
      } 
      else if (response.status == 401) {
        await refersh()
        setSearchCheck(true)
        
      }
      else {
        alert('Ошибка при отправке данных');
        setSearchCheck(true)
        console.log(response.status)
      } 
      } catch (error) {
        setSearchCheck(true)
        console.error('Ошибка:', error);

      }
    }

    async function getId(){
      try {
      const response = await fetch(`http://localhost:8080/api/users/id/${localStorage.getItem("token")}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${localStorage.getItem("token")}`,
          'Content-Type': 'application/json'
        },
      });

      if (response.ok) {
        const data = await response.json();
        await setuserId(data)
      } 
      else if (response.status == 401) {
        await refersh()
        setSearchCheck(true)
      }
      else {
        alert('Ошибка при отправке данных');
        setSearchCheck(true)
        console.log(response.status)
      } 
      } catch (error) {
        setSearchCheck(true)
        console.error('Ошибка:', error);

      }
    }

    async function getAllChats(){
      try{
        const response = await fetch(`http://localhost:8080/api/chats/${await getId()}`, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${localStorage.getItem("token")}`,
            'Content-Type': 'application/json'
          },
        })

        if (response.ok){
          const data = await response.json()
          await setUserChats([data])
        }

        else {
          setSearchCheck(true)
          console.log(response.status)
        } 

      } catch(error){
        setSearchCheck(true)
        console.error('Ошибка:', error);
      }
    } 

    // useEffect(() => {
    //   getAllChats();
    // }, []);    

    useEffect(() => {
      getId()
      getAllChats()
    }, [])

    return(
      <>
      {isOpenSettings ? 
          <div className='chats_block'>
            <div className="folder_block">
                <div className="user_block">
                    <div className="user_folder_photo" onClick={() => openSettings(isOpenSettings)}></div>
                    <input className="user_folder_search" placeholder='Поиск' value={search} onChange={handleChange}/>
                    <button onClick={searchUser}>src</button>
                </div>

                
                <div className="folder">
                    {searchCheck ? 
                      userChats.map(item => 
                        <ChatInfo
                            name={item.name}
                            // text = {item.text}
                            // time={item.time}
                            // img={item.img}
                            func={() => clickChat(isClick, item.id)}
                        />
                      )
                      : 
                      <div>
                        <ChatInfo
                            name={userInfo[0].username}
                            text = {""}
                            time={""}
                            img={""}
                            func={() => clickChat(isClick, userInfo[0].id)}
                          /> 
                      </div>
                    }
                </div>
            </div>
            <div className='chat_block'>
              {
                isClick ? 
                <div></div>
                :
                searchCheck ? 
                      userChats.map(item => 
                        <Chat
                            user1 = {userid}
                            uer2 = {item.id}
                            username = {item.username}
                        />
                      )
                      : 
                      <div>
                        <Chat
                            user1 = {userid}
                            uer2 = {userInfo[0].id}
                            username = {userInfo[0].username}
                          /> 
                      </div>
              }
            </div>
          </div>
          :
          <div className='change_info_form'>
              <Settings
                exit = {() => openSettings(isOpenSettings)} 
                userId = {userid}
              />
          </div>
      }
        
      </>
    )
    
}

export default Chats;
