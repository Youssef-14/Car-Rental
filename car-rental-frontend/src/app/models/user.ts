export interface User {
  id: number
  firstname: string
  lastname: string
  email: string
  number: string
  createdAt: Date
  updatedAt: Date
  driverLicenseNumber: string
  licenseImage: string | ArrayBuffer | null
  isVerified: boolean
}
