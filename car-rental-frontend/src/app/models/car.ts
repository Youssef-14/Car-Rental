export interface Car {
  id: number
  brand: string
  color: string
  name: string
  type: string
  transmission: string
  description: string
  price: number
  year: number
  image: File | null
  returnedImage: string | null
  available: boolean
  imageUrl?: string // Optional field for the image URL
  createdAt: Date
  updatedAt: Date
  processedImage: String
}
