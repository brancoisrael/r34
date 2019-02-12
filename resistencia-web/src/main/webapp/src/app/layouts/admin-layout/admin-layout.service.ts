import {NgModule} from '@angular/core'
import {MessageService} from 'primeng/api';

import { MembroService } from 'app/membros/membros.service';


@NgModule({
    providers:[MembroService,MessageService]
})

export class AdminLayoutService{}