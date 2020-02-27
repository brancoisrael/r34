import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule , LOCALE_ID } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import {AgmCoreModule} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AdminLayoutService } from './layouts/admin-layout/admin-layout.service';
import { HttpModule } from '@angular/http';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,     
    ReactiveFormsModule,
    HttpClientModule,
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
    AdminLayoutComponent,    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
