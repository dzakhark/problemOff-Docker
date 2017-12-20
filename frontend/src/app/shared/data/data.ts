export class Data {
  private _apiLinks = {
    admin: {
      getMainCategories: 'http://localhost:8080/api/admin/categories/main'
    },
    user: {
      getCategories: 'http://localhost:8080/api/user/categories',
      loginUrl: 'http://localhost:8080/api/login',
      registrationUrl: 'http://localhost:8080/api/register'
    }
  };


  get apiLinks() {
    return this._apiLinks;
  }
}
