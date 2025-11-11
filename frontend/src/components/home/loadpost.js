export default function LoadPost() {
    const[posts, setPosts] = useState([]);
    const[ads, setAds] = useState([]);
    let token=localStorage.getItem('token');
    const loadpost=async()=>{
        const response=await fetch('http://localhost:5000/api/posts/fetchallposts',{
            method:'GET',
            headers:{
                'Content-Type':'application/json',
                'auth-token':token
            }
        });
        const json=await response.json();
        setPosts(json);
    }
    const loadads=async()=>{
        const response=await fetch('http://localhost:5000/api/posts/fetchallads',{
            method:'GET',
            headers:{
                'Content-Type':'application/json',
                'auth-token':token
            }
        });
        const json=await response.json();
        setAds(json);
    }
  return (
    <div>
        <div className="posts-container">
        {posts.map((post) => (
            <Post key={post._id} post={post} />
        ))}
        </div>
        <div className="ads-container">
        {ads.map((ad) => (
            <Ad key={ad._id} ad={ad} />
        ))}
        </div>
    </div>
    );
    }   