import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule , LOCALE_ID } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, PreloadingStrategy, PreloadAllModules } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import {AgmCoreModule} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { MembrosComponent } from './membros/membros.component';
import { MembroService } from './membros/membros.service';
import { AdminLayoutService } from './layouts/admin-layout/admin-layout.service';


@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,     
    ReactiveFormsModule,
    HttpModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    }),
    AdminLayoutService
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
