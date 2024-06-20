// src/app/models/user.model.ts
export interface RoleModel {
    id: number;
    name: string;
  }
  
  export interface User {
    userId: number;
    fullName: string;
    email: string;
    phone: string;
    address: string;
    roles: RoleModel[];
  }
  