import { Component, OnInit } from '@angular/core';
import { SideBarService } from './sidebar.service';
import { EndPointDTO } from 'app/shared/dto/endpont.dto';
import { map } from 'rxjs-compat/operator/map';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/membros', title: 'Membros',  icon: 'person', class: '' },
    { path: '/lancamento-novo', title: 'Lançamentos',  icon: 'library_books', class: '' },
    
    /*{ path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: '/user-profile', title: 'User Profile',  icon:'person', class: '' },
    { path: '/table-list', title: 'Table List',  icon:'content_paste', class: '' },
    { path: '/typography', title: 'Typography',  icon:'library_books', class: '' },
    { path: '/icons', title: 'Icons',  icon:'bubble_chart', class: '' },
    { path: '/maps', title: 'Maps',  icon:'location_on', class: '' },
    { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
    { path: '/upgrade', title: 'Upgrade to PRO',  icon:'unarchive', class: 'active-pro' },
    */
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(private sideBarService: SideBarService) { }

  ngOnInit() {
    
    this.menuItems = []
    
    if(window.sessionStorage.getItem('token')===null || window.sessionStorage.getItem('token')==='null')
      return;

    this.sideBarService.buscarMenuMembro()
    .subscribe((response:EndPointDTO[])=>{
      for(let dto of response){
        for(let r of ROUTES)
          if(dto.url === r.path)
            this.menuItems.push(r)
      }
    })

    //this.menuItems = ROUTES.filter(menuItem => menuItem);
    console.log(this.menuItems)
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}
