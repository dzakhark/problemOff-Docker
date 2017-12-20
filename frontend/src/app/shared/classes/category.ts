export class Category {
  constructor(public name: string, public types: {name: string}[], public subCategories: Category[], public _links: any) {}
}
