import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { TableListComponent } from '../../table-list/table-list.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import { MembrosComponent } from '../../membros/membros.component';
import { MembroNovoComponent } from '../../membros/novo/membro-novo.component';
import { LancamentoNovoComponent } from '../../lancamento/novo/lancamento-novo.component';
import { LoginComponent } from 'app/membros/login/login.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'table-list',     component: TableListComponent },
    { path: 'typography',     component: TypographyComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'upgrade',        component: UpgradeComponent },

    { path: 'membros',        component: MembrosComponent },
    { path: 'membro-novo',    component: MembroNovoComponent },
    { path: 'membro-novo/:id',component: MembroNovoComponent ,
        children:[
            {path:'membro-novo/:id',component:MembroNovoComponent}
        ]
    },
    {path: 'login', component:LoginComponent},
    {path: 'lancamento-novo',      component: LancamentoNovoComponent },

   
];
