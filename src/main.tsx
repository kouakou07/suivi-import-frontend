import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import { BrowserRouter, Route, Routes} from 'react-router-dom';
import Layout from './pages/template/Layout';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
   <BrowserRouter>
      <App/>
   </BrowserRouter>
  </React.StrictMode>,
)
