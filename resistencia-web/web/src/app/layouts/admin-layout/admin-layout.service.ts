import {NgModule} from '@angular/core'
import {MessageService, ConfirmationService} from 'primeng/api';

import { MembroService } from '../../membros/membros.service';
import {ProdutoService} from '../../produto/produto.service';
import {LancamentoService} from '../../lancamento/lancamento.service';

@NgModule({
    providers:[MembroService,ProdutoService,LancamentoService,
        MessageService,ConfirmationService]
})

export class AdminLayoutService{}