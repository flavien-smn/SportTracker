export interface User {
  id: string;
  email: string;
  role: Role;
}

export type Role = 'USER' | 'ADMIN';
