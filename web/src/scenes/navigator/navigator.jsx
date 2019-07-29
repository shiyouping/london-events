import classnames from "classnames";
import React, { PureComponent } from "react";
import {
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Container,
  Row,
  Col,
  Jumbotron,
  Pagination,
  PaginationItem,
  PaginationLink,
  Spinner
} from "reactstrap";

import "scenes/navigator/navigator.css";
import EventTab from "components/event-tab/event-tab";
import EventService from "services/rest/event-service";
import ResponseResolver from "kernel/network/response-resolver";

const CATEGORY_MUSIC = "music";
const CATEGORY_CONFERENCE = "conference";
const CATEGORY_COMEDY = "comedy";
const CATEGORY_EDUCATION = "learning_education";
const CATEGORY_FESTIVALS = "festivals_parades";
const CATEGORY_FILM = "movies_film";
const CATEGORY_FOOD = "food";
const CATEGORY_HOLIDAY = "holiday";
const CATEGORY_BOOKS = "books";
const CATEGORY_ATTRACTIONS = "attractions";
const CATEGORY_COMMUNITY = "community";

export default class Event extends PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      spinner: true,
      activeTab: CATEGORY_MUSIC,
      page: 0,
      size: 6,
      totalPages: 0,
      events: {}
    };

    this.eventService = new EventService();
    this.toggle = this.toggle.bind(this);
    this.onPaginationClick = this.onPaginationClick.bind(this);
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState(
        {
          activeTab: tab,
          page: 0,
          totalPages: 0
        },
        () => {
          this.getEvents();
        }
      );
    }
  }

  async onPaginationClick(page) {
    if (page !== this.state.page && page >= 0 && page < this.state.totalPages) {
      await this.setState({ page });
      await this.getEvents();
    }
  }

  getSpinner() {
    if (this.state.spinner) {
      return (
        <Container>
          <br />
          <Row>
            <Col sm="12" md={{ size: 2, offset: 5 }}>
              <Spinner color="primary" />
            </Col>
          </Row>
          <br />
        </Container>
      );
    }
  }

  getNav() {
    return (
      <Nav tabs>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_MUSIC
            })}
            onClick={() => {
              this.toggle(CATEGORY_MUSIC);
            }}
          >
            Music
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_CONFERENCE
            })}
            onClick={() => {
              this.toggle(CATEGORY_CONFERENCE);
            }}
          >
            Conference
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_COMEDY
            })}
            onClick={() => {
              this.toggle(CATEGORY_COMEDY);
            }}
          >
            Comedy
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_EDUCATION
            })}
            onClick={() => {
              this.toggle(CATEGORY_EDUCATION);
            }}
          >
            Education
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FESTIVALS
            })}
            onClick={() => {
              this.toggle(CATEGORY_FESTIVALS);
            }}
          >
            Festivals
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FILM
            })}
            onClick={() => {
              this.toggle(CATEGORY_FILM);
            }}
          >
            Film
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_FOOD
            })}
            onClick={() => {
              this.toggle(CATEGORY_FOOD);
            }}
          >
            Food
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_HOLIDAY
            })}
            onClick={() => {
              this.toggle(CATEGORY_HOLIDAY);
            }}
          >
            Holiday
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_BOOKS
            })}
            onClick={() => {
              this.toggle(CATEGORY_BOOKS);
            }}
          >
            Books
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_ATTRACTIONS
            })}
            onClick={() => {
              this.toggle(CATEGORY_ATTRACTIONS);
            }}
          >
            Attractions
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({
              active: this.state.activeTab === CATEGORY_COMMUNITY
            })}
            onClick={() => {
              this.toggle(CATEGORY_COMMUNITY);
            }}
          >
            Community
          </NavLink>
        </NavItem>
      </Nav>
    );
  }

  getTabContent() {
    return (
      <TabContent activeTab={this.state.activeTab}>
        <TabPane tabId={CATEGORY_MUSIC}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_CONFERENCE}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_COMEDY}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_EDUCATION}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FESTIVALS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FILM}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_FOOD}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_HOLIDAY}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_BOOKS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_ATTRACTIONS}>
          <EventTab events={this.state.events} />
        </TabPane>
        <TabPane tabId={CATEGORY_COMMUNITY}>
          <EventTab events={this.state.events} />
        </TabPane>
      </TabContent>
    );
  }

  getPagination() {
    if (this.state.spinner) {
      // Don't show spinner and pagination at the same time
      return null;
    }

    return (
      <Row>
        <Col sm="12" md={{ size: 4, offset: 5 }}>
          <Pagination aria-label="Page navigation">
            <PaginationItem>
              <PaginationLink
                first
                onClick={() => {
                  this.onPaginationClick(0);
                }}
              />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink
                previous
                onClick={() => {
                  this.onPaginationClick(this.state.page - 1);
                }}
              />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink
                next
                onClick={() => {
                  this.onPaginationClick(this.state.page + 1);
                }}
              />
            </PaginationItem>
            <PaginationItem>
              <PaginationLink
                last
                onClick={() => {
                  this.onPaginationClick(this.state.totalPages - 1);
                }}
              />
            </PaginationItem>
          </Pagination>
        </Col>
      </Row>
    );
  }

  async getEvents() {
    await this.setState({ events: {}, spinner: true });
    let response = await this.eventService.getLondonEvents(
      this.state.activeTab,
      this.state.page,
      this.state.size
    );

    if (ResponseResolver.isPositive(response)) {
      let { content, number, totalPages } = response.data;
      this.setState({
        events: content,
        spinner: false,
        totalPages: totalPages,
        page: number
      });
    }
  }

  componentDidMount() {
    this.getEvents();
  }

  render() {
    return (
      <Container>
        <Jumbotron className="background">
          <h1 className="text-effect">London Events</h1>
          <p className="text-effect">Events and things to do in London.</p>
        </Jumbotron>
        {this.getNav()}
        {this.getTabContent()}
        {this.getSpinner()}
        {this.getPagination()}
      </Container>
    );
  }
}
