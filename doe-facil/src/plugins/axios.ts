import { getToken, removeToken } from "@/token";
import axios from "axios";

export const apiUrls = {
  ongs: import.meta.env.VITE_ONGS_API_URL || "http://localhost:8082",
  colaboradores: import.meta.env.VITE_COLABORADORES_API_URL || "http://localhost:8081",
  doacoes: import.meta.env.VITE_DOACOES_API_URL || "http://localhost:8083",
};

export const api = axios.create();

api.interceptors.request.use(
  (config) => {
    const token = getToken();
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response?.status === 401) {
      removeToken();
      localStorage.removeItem("Expiration");
      localStorage.removeItem("idOng");
      window.location.href = "/";
    }
    return Promise.reject(error);
  }
);
