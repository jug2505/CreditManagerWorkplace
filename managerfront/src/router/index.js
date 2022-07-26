import { createRouter, createWebHashHistory } from 'vue-router'
import ProposalView from '../views/ProposalView.vue'
import FindView from '../views/FindView.vue'
import ContractsView from '../views/ContractsView.vue'
import AllProposalsView from '../views/AllProposalsView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: ProposalView
  },
  {
    path: '/find',
    name: 'find',
    component: FindView
  },
  {
    path: '/contract',
    name: 'contract',
    component: ContractsView
  },
  {
    path: '/proposal',
    name: 'proposal',
    component: AllProposalsView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
