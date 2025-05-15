import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import AddListingPage from "./pages/AddListingPages";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import HomePage from "./pages/HomePage";


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/add-listing" element={<AddListingPage />} />
        {/* Placeholder for future search page */}
        <Route path="/search" element={<div>Suche Seite (bald verfügbar)</div>} />
      </Routes>
    </Router>
  );
}

export default App;