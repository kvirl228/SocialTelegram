import Registration from "./Components/Forms/Registration";
import Login from "./Components/Forms/Login";
import Chats from "./Components/Chats/chats";
import { Routes, Route, Router } from 'react-router-dom';
import './App.css'
import Settings from "./Components/Forms/Settings";

function App() {
  return (
    <>
    {/* <Router> */}
      <Routes>
          <Route path="/" element={<Registration/>}/>
          <Route path="/login" element={<Login/>}/> 
          <Route path="/chats" element={<Chats/>}/>
          <Route path="/set" element={<Settings/>}/>
        </Routes>
      {/* </Router> */}
    </>
  );
}

export default App;