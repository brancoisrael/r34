import { NgModule, ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';

import {DropdownModule} from 'primeng/dropdown';
import {CalendarModule} from 'primeng/calendar';
import {InputSwitchModule} from 'primeng/inputswitch';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TableModule} from 'primeng/table';
//remover
import {
  MatButtonModule,
  MatInputModule,
  MatRippleModule,
  MatFormFieldModule,
  MatTooltipModule,
  MatSelectModule
} from '@angular/material';

import { MembrosComponent } from '../../membros/membros.component';
import { MembroNovoComponent } from '../../membros/novo/membro-novo.component';
import { LancamentoNovoComponent } from '../../lancamento/novo/lancamento-novo.component';
import { LoginComponent } from 'app/membros/login/login.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    DropdownModule,
    CalendarModule,
    InputSwitchModule,
    MessagesModule,
    MessageModule,
    ConfirmDialogModule,
    TableModule
    
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    TableListComponent,
    TypographyComponent,
    IconsComponent,
    MapsComponent,
    NotificationsComponent,
    UpgradeComponent,
    MembrosComponent,
    MembroNovoComponent,
    LancamentoNovoComponent,
    LoginComponent
  ],
  exports:[
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownModule,
    CalendarModule,
    InputSwitchModule,
    MessagesModule,
    MessageModule,
    ConfirmDialogModule,
    TableModule
  ]  
})

export class AdminLayoutModule {
  static forRoot():ModuleWithProviders{
    return{
        ngModule:AdminLayoutModule,
        providers:[]
    }
}
}
