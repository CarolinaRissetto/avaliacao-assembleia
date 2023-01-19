import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import { CadastrarPautaScreen } from './ui/screens/cadastrar-pauta/cadastrar-pauta';
import { ChakraProvider } from '@chakra-ui/react'
import { ListarPautasScreen } from './ui/screens/listar-pautas/listar-pautas';
import { AbrirSessaoScreen } from './ui/screens/sessao-votacao/sessao-votacao';


const router = createBrowserRouter([
  {
    path: "/",
    element: <ListarPautasScreen />,
  },
  {
    path: "/cadastrar-pauta",
    element: <CadastrarPautaScreen />,
  },
  {
    path: "/abrir-sessao",
    element: <AbrirSessaoScreen />,
  }
]);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <ChakraProvider>
      <RouterProvider router={router} />
    </ChakraProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
