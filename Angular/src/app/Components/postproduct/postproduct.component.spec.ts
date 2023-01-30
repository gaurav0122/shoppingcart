import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';

import { PostproductComponent } from './postproduct.component';
import { PostProductServiceService } from './service/post-product-service.service';

describe('PostproductComponent', () => {
  let component: PostproductComponent;
  let fixture: ComponentFixture<PostproductComponent>;
  let mockdashboardService :any;
  let mockpostProductService:any;
  beforeEach(async () => {
    mockdashboardService = jasmine.createSpyObj(DashboardserviceService,['']);
    mockpostProductService = jasmine.createSpyObj(PostProductServiceService,['']);

    await TestBed.configureTestingModule({
      declarations: [ PostproductComponent ],
      providers:[
        {
          provide:DashboardserviceService,
          useValue:mockdashboardService
        },
        {
          provide:PostProductServiceService,
          useValue:mockpostProductService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostproductComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
