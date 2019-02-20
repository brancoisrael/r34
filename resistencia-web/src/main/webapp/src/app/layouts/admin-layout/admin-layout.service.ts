import {NgModule} from '@angular/core'
import {MessageService, ConfirmationService} from 'primeng/api';

import { MembroService } from 'app/membros/membros.service';


@NgModule({
    providers:[MembroService,MessageService,ConfirmationService]
})

export class AdminLayoutService{}