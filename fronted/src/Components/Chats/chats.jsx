import { useEffect, useState } from 'react';
import './Chat.css'
import ChatInfo from './Chat_info';
import { useNavigate } from 'react-router-dom';

function Chats(){

    const navigate = useNavigate()

    const[search, setSearch] = useState('')
    const[searchCheck, setSearchCheck] = useState(true)
    // const[chooseCaht, setChooseChat] = useState(-1)
    const[userInfo, setUserInfo] = useState([{username:'', Id:-1}])
    const[userChats, setUserChats] = useState([])

    const handleChange = (e) => {
      setSearch(e.target.value)
      if(e.target.value.trim() == ''){
                setSearchCheck(true)

      }
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
        console.log(userInfo[0].Id)
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
        console.log(data)
        return await data
      } 
      else if (response.status == 401) {
        await refersh()
        setSearchCheck(true)
      }
      else {
        alert('Ошибка при отправке данных');
        setSearchCheck(true)
        alert("sadasd")
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

    const [chooseChat, setchooseChat] = useState(0);
    function clickHandler(id){
        setchooseChat(id)
    }

    useEffect(() => {
      getAllChats();
    }, []);    

    return(
        <div className='chats_block'>
            <div className="folder_block">
                <div className="user_block">
                    <div className="user_folder_photo"></div>
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
                            func={() => clickHandler(item.chatId)}
                        />
                      )
                      : 
                      <div>
                        <ChatInfo
                            name={userInfo[0].username}
                            text = {""}
                            time={""}
                            img={""}
                            func={() => clickHandler(userInfo[0].Id)}
                          /> 
                      </div>
                    }
                </div>
            </div>
            <div className='chat_block'>
                
            </div>
        </div>
    )
    
}

export default Chats;
