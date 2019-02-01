import { HttpHeaders } from "@angular/common/http";

export const R34_API='http://localhost:8080/resistencia-ws'

 export const HTTP_HEADER: HttpHeaders = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Accept', 'application/json')
      .set('Authorization', 'Bearer xk21bPa9jQ')
     ;