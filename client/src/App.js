import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Layout from './components/layout/js/Layout';
import Home from './components/home/js/Home';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
