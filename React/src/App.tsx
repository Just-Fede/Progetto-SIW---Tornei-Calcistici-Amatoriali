import { Routes, Route, Navigate } from "react-router-dom";
import TorneiPage from "./pages/TorneiPage";
import TorneoPage from "./pages/TorneoPage";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/tornei" />} />
      <Route path="/tornei" element={<TorneiPage />} />
      <Route path="/tornei/:id" element={<TorneoPage />} />
    </Routes>
  );
}