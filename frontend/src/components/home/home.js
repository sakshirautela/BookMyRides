import { Navigate } from "react-router-dom";
export default function Home() {
    const gotoProfile=()=>{
        <Navigate to="/profile" />
    }
const gotoRides=()=>{
    <Navigate to="/rides" />
}
const gotoChats=()=>{
    <Navigate to="/chats" />
}
const gotoMap=()=>{
    <Navigate to="/map" />
}
  return (<>
    <div>
      <h1>Welcome to Book My Rides</h1>
      <button>Home</button>
      <button onClick={gotoProfile}>Profile</button>
      <button onClick={gotoRides}>Rides</button>
      <button onClick={gotoChats}>Chats</button>
    </div>ß
  </>)
}