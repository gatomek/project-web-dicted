import React from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";

import Home from "./pages/Home";
import About from "./pages/About";
import Dict from "./pages/Dict";

function App() {
  return (
    <>
        <Navbar bg="dark" data-bs-theme="dark" className="sticky-nav" fixed="top">
            <Container>
                <Navbar.Brand href="/home">Web Dicted</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/home">Home</Nav.Link>
                    <Nav.Link href="/dict">Dict</Nav.Link>
                    <Nav.Link href="/about">About</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
        <div className="container mt-2" style={{ marginTop: 40 }}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/dict" element={<Dict />} />
                    <Route path="/about" element={<About />} />
                </Routes>
            </BrowserRouter>
        </div>
    </>
  );
}

export default App;
