import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Nav from './components/Nav'
import GalleryMasonry from './components/GalleryMasonry'
import "tailwindcss/tailwind.css";
import './App.css';

function App() {
  return (
    <>
      <div className="App">
        <Nav />
        <GalleryMasonry />
      </div>

    </>
  );
}

export default App;
