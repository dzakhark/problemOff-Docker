export class Categories {
  constructor(public name: string, public types: {name: string}[], public subCategories: Categories[]) {}
}
  
  