import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import Saved from './pages/Saved';
import InProgress from './pages/InProgress';
import Filter from './pages/Filter';
import Feedback from './pages/Feedback';
import UserData from './pages/UserData';
import ChatDetail from "./pages/ChatDetail";
import NewInserat from './pages/NewInserat';
import InseratCreate from './pages/InseratCreate';
import ListingDetail from './pages/ListingDetail';


const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<Login />} />
    <Route path="/register" element={<Register />} />
    <Route path="/saved" element={<Saved />} />
    <Route path="/in-progress" element={<InProgress />} />
    <Route path="/filter" element={<Filter />} />
    <Route path="/inserat-creating" element={<InseratCreate />} />
    <Route path="/inserat/new" element={<NewInserat />} />
    <Route path="/help-feedback" element={<Feedback />} />
    <Route path="/user-data" element={<UserData />} />
    <Route path="/chat/:id" element={<ChatDetail />} />
    <Route path="/listing/:id" element={<ListingDetail />} />
  </Routes>
);

export default AppRoutes;
