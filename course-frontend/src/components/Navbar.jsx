import { Link, useNavigate } from "react-router-dom";

function Navbar(){
    const navigate = useNavigate();
    const token = localStorage.getItem("token");
    
    function handleLogout() {
        localStorage.removeItem("token");
        navigate("/login");
    }
    
    return ( 
        <nav className="navbar">
            <div className="nav-brand">Course App</div>

            <div className="nav-links">
                <Link to="/">Home</Link>
                <Link to="/courses">Courses</Link>
                <Link to="/dashboard">Dashboard</Link>
                {token ? (
                    <button onClick={handleLogout}>Logout</button>
                ) : (
                    <Link to="/login">Logout</Link>
                )}
                <Link to="/login">Login</Link>
            </div>
        </nav>
    );
}
export default Navbar;